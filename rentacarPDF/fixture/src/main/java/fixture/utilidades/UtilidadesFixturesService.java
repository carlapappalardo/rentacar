package fixture.utilidades;

import org.apache.isis.applib.AbstractService;
import dom.autos.MarcasServicio;

public class UtilidadesFixturesService extends AbstractService {
	
	public String install() {
	final UtilidadesFixture fix=new UtilidadesFixture();
    fix.setContainer(getContainer());
    fix.setMarcasServicio(marcasServicio);
    fix.install();
    return ""; }
	// }}
	
    
	// {{ injected: MarcasServicio
 	private MarcasServicio marcasServicio;

 	public void setMarcasServicio(final MarcasServicio marcasServicio) {
 	   this.marcasServicio = marcasServicio; }
 	// }}
}
