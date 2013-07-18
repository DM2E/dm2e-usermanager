package de.mpiwg.dm2e.userManager.utils.map.duplex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DuplexMap<V> implements IDuplexMap<V>{

	private Map<DuplexKey, V> map;
	private Map<Object, List<DuplexKey>> mapFirstKey;
	private Map<Object, DuplexKey> mapOwnKey;
	
	public DuplexMap(){
		this.map  = new HashMap<DuplexKey, V>();
		this.mapFirstKey = new HashMap<Object, List<DuplexKey>>();
		this.mapOwnKey = new HashMap<Object, DuplexKey>();
	}
	
	public DuplexMap(DuplexMap<? extends V> m) {
		this.map  = new HashMap<DuplexKey, V>();
		this.mapFirstKey = new HashMap<Object, List<DuplexKey>>();
		this.mapOwnKey = new HashMap<Object, DuplexKey>();
		this.putAllForCreate(m);
	}

	private void putAllForCreate(DuplexMap<? extends V> m) {
		for(Map.Entry<DuplexKey, ? extends V> e : m.entrySet()){
			DuplexKey tKey = e.getKey(); 
			 this.map.put(tKey, e.getValue());
			 this.mapOwnKey.put(tKey.getOwnKey(), tKey);
			 
			 if(!mapFirstKey.containsKey(tKey.getFirstKey())){
				 mapFirstKey.put(tKey.getFirstKey(), new LinkedList<DuplexKey>());
			 }
			 if(!mapFirstKey.get(tKey.getFirstKey()).contains(tKey)){
				 mapFirstKey.get(tKey.getFirstKey()).add(tKey);	 
			 }
		}
	}
	
	public List<V>getValuesByFirstKey(Object srcKey){
		List<V> list = new ArrayList<V>();
		if(mapFirstKey.containsKey(srcKey)){
			for(DuplexKey tKey : mapFirstKey.get(srcKey)){
				list.add(map.get(tKey));
			}
		}
		return list;
	}
	
	public V getValuesByOwnKey(Object ownKey){
		DuplexKey tKey = mapOwnKey.get(ownKey);
		if(tKey != null){
			return this.map.get(tKey);
		}
		return null;
	}
	
	public Set<DuplexKey> keySet(){
		return this.map.keySet();
	}
	
	public Set<Map.Entry<DuplexKey, V>> entrySet() {
		return this.map.entrySet();
	}	
	
	@Override
	public int size() {
		return this.map.size();
	}

	@Override
	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	@Override
	public boolean containsKey(DuplexKey key) {
		return this.map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return this.map.containsValue(value);
	}

	@Override
	public V get(DuplexKey key) {
		return map.get(key);
	}

	@Override
	public V put(DuplexKey tKey, V value) {
		if(!mapFirstKey.containsKey(tKey.getFirstKey())){
			List<DuplexKey> list = new ArrayList<DuplexKey>();
			mapFirstKey.put(tKey.getFirstKey(), list);
		}
		if(!mapFirstKey.get(tKey.getFirstKey()).contains(tKey)){
			mapFirstKey.get(tKey.getFirstKey()).add(tKey);
		}
		
		this.mapOwnKey.put(tKey.getOwnKey(), tKey);
		return this.map.put(tKey, value);
	}

	@Override
	public V remove(DuplexKey key) {
		if(mapFirstKey.containsKey(key.getFirstKey())){
			mapFirstKey.get(key.getFirstKey()).remove(key);
		}
		this.mapOwnKey.remove(key.getOwnKey());
		return this.map.remove(key);
	}

	@Override
	public void clear() {
		this.map.clear();
		this.mapFirstKey.clear();
		this.mapOwnKey.clear();
	}

	@Override
	public Collection<V> values() {
		return this.map.values();
	}

	
}
