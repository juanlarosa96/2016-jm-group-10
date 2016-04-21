package POI;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class ComunaTest {

	private Point posicion1;
	private Point posicion4;

	private Comuna comuna10;
	private List<Point> limitesComuna10;

	@Before
	public void init() {

		posicion1 = new Point(-34.6184994, -58.4368164);
		posicion4 = new Point(-34.6327475, -58.4851585);

		limitesComuna10 = new ArrayList<Point>() {
			{
				add(new Point(-34.611015, -58.529025));
				add(new Point(-34.615256, -58.531221));
				add(new Point(-34.634217, -58.529806));
				add(new Point(-34.634495, -58.510883));
				add(new Point(-34.639959, -58.509675));
				add(new Point(-34.645743, -58.502325));
				add(new Point(-34.643387, -58.497065));
				add(new Point(-34.644764, -58.495284));
				add(new Point(-34.636925, -58.478568));
				add(new Point(-34.638797, -58.476058));
				add(new Point(-34.636537, -58.471423));
				add(new Point(-34.622482, -58.477860));
				add(new Point(-34.624637, -58.482710));
				add(new Point(-34.614536, -58.495627));
				add(new Point(-34.609238, -58.500219));
				add(new Point(-34.616726, -58.513008));
				add(new Point(-34.620399, -58.516870));
			}
		};

		comuna10 = new Comuna(10, limitesComuna10);

	}

	@Test
	public void Comuna10IncluyeABancoProvincia() {
		Assert.assertTrue(comuna10.incluyeA(posicion4));
	}

	@Test
	public void Comuna10NoIncluyeAMacowins() {
		Assert.assertFalse(comuna10.incluyeA(posicion1));
	}

}
