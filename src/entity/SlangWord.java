package entity;

public class SlangWord {
	
	public String keyCode;
	public String keyDefination;
	
	public String getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	public String getKeyDefination() {
		return keyDefination;
	}
	public void setKeyDefination(String keyDefination) {
		this.keyDefination = keyDefination;
	}
	public SlangWord(String keyCode, String keyDefination) {
		super();
		this.keyCode = keyCode;
		this.keyDefination = keyDefination;
	}
	public SlangWord() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyCode == null) ? 0 : keyCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SlangWord other = (SlangWord) obj;
		if (keyCode == null) {
			if (other.keyCode != null)
				return false;
		} else if (!keyCode.equals(other.keyCode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SlangWord [keyCode=" + keyCode + ", keyDefination=" + keyDefination + "]";
	}

}
