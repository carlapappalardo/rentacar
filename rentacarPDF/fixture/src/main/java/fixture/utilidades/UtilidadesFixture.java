package fixture.utilidades;

import org.apache.isis.applib.fixtures.AbstractFixture;


import dom.autos.Marcas;
import dom.autos.MarcasServicio;


public class UtilidadesFixture extends AbstractFixture{

	@Override
    public void install() {
		crearMarca("Ferrari");
		//removeAllToDosForCurrentUser();        
        getContainer().flush(); }
	// }}
	
	// {{ Helpers
	private Marcas crearMarca(final String marcas){
		return marcasServicio.CargarMarca(marcas);
	 }
	// }}

	// {{ injected: MarcasServicio
	private MarcasServicio marcasServicio;

	public void setMarcasServicio(final MarcasServicio marcasServicio) {
	   this.marcasServicio = marcasServicio; }
	// }}
}
