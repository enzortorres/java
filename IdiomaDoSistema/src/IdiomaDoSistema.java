import java.util.Locale;

public class IdiomaDoSistema {
	public static void main(String[] args) {
		Locale loc = Locale.getDefault();
		System.out.println("Your system are in " +  loc.getDisplayLanguage());
	}
}