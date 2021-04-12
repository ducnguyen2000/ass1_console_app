package CheckExistence;

@SuppressWarnings("hiding")
public class Pair<Boolean, Integer> {
	public final Boolean isExisted;
	public final Integer index;
	
	public Pair(Boolean isExisted, Integer index) {
		this.isExisted = isExisted;
		this.index = index;
	}
}
