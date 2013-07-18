package de.mpiwg.dm2e.userManager.utils.map.duplex;


public class DuplexKey {
	
	private Object firstKey;
	private Object ownKey;
	
	public DuplexKey(Long firstKey, Long ownKey){
		this.firstKey = firstKey;
		this.ownKey = ownKey;
	}
	
	public DuplexKey(String firstKey, Long ownKey){
		this.firstKey = firstKey;
		this.ownKey = ownKey;
	}
	
	public DuplexKey(Object firstKey, Object ownKey){
		this.firstKey = firstKey;
		this.ownKey = ownKey;
	}
	

	public boolean equalsFirstKey(Object key){
		if(firstKey != null && key != null){
			return firstKey.equals(key);
		}else if(firstKey == null && key == null){
			return true;
		}
		return false;
	}
	
	public boolean equalsOwnKey(Object key){
		if(ownKey != null && key != null){
			return ownKey.equals(key);
		}else if(ownKey == null && key == null){
			return true;
		}
		return false;
	}
	
	public Object getFirstKey() {
		return firstKey;
	}

	public void setFirstKey(Object firstKey) {
		this.firstKey = firstKey;
	}

	public Object getOwnKey() {
		return ownKey;
	}

	public void setOwnKey(Object ownKey) {
		this.ownKey = ownKey;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof DuplexKey){
			DuplexKey other = (DuplexKey)o;
			
			if(this.equalsOwnKey(other.getOwnKey()) &&
					this.equalsFirstKey(other.getFirstKey())){
				return true;
			}
		}
		return false;
	}
}
