package de.mpiwg.dm2e.userManager.utils.map.duplex;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface IDuplexMap<V> {

	int size();
	
	boolean isEmpty();

	boolean containsKey(DuplexKey key);

	boolean containsValue(Object value);
	
	V get(DuplexKey key);
	
	V put(DuplexKey key, V value);

	V remove(DuplexKey key);

	//void putAll(Map<? extends K, ? extends V> m);

	void clear();

	Set<DuplexKey> keySet();

	Collection<V> values();

	Set<Map.Entry<DuplexKey, V>> entrySet();

	boolean equals(Object o);

	/**
	  * Returns the hash code value for this map.  The hash code of a map is
	  * defined to be the sum of the hash codes of each entry in the map's
	  * <tt>entrySet()</tt> view.  This ensures that <tt>m1.equals(m2)</tt>
	  * implies that <tt>m1.hashCode()==m2.hashCode()</tt> for any two maps
	  * <tt>m1</tt> and <tt>m2</tt>, as required by the general contract of
	  * {@link Object#hashCode}.
	  *
	  * @return the hash code value for this map
	  * @see Map.Entry#hashCode()
	  * @see Object#equals(Object)
	  * @see #equals(Object)
	  */
	int hashCode();
	
}
