import javax.swing.JButton;

public class Pair<T1, T2> {
	public final T1 x;
	public final T2 y;
	
	public Pair(JButton[] p, T2 y) {
		T1 p2 = extracted(p);
		this.x = p2;
		this.y = y;
	}

	@SuppressWarnings("unchecked")
	private T1 extracted(JButton[] p) {
		return (T1) p;
	}
}
