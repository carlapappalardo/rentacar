package dom.autos;


import java.util.Date;
import java.util.List;

import org.apache.isis.applib.AbstractContainedObject;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import dom.autos.Autos;
import dom.autos.Autos.Estado;
import dom.autos.Autos.Seguro;
import dom.autos.Autos.TipoCombustible;
import dom.autos.Marcas;

@Named("Autos")
public class AutosServicio extends AbstractContainedObject
{

	 
	//{{ Autos
	 
	@MemberOrder(sequence = "1")
	public Autos CargarAuto(
			@Named("Patente") String patente,
			@Named("Marca") Marcas marca, 
			@Named("Modelo") String modelo, 
			@Named("Año") int ano,
			@Named("Color") String color,
			@Named("Kilometraje") int kms,
			@Named("Capacidad Baul (lt)") int baul,
			@Named("Tipo de Combustible") TipoCombustible combustible,
			@Named("Estado de Alquiler") Estado estado,
			@Named("Fecha de Compra") Date fechaCompra,
			@Named("Compañía de Seguro")Seguro seguro)
			//@Named("Categoria")Autos categoria)
	       
	   { final String ownedBy = currentUserName();
	     return elAuto(patente,marca,modelo,ano,color,kms,baul,combustible,estado,fechaCompra,seguro,ownedBy);
	   }
		 
	@Hidden // for use by fixtures
	public Autos elAuto(
		   String patente,
		   Marcas marca,
		   String modelo,
		   int ano,
		   String color,
		   int kms, 
		   int baul,
		   TipoCombustible combustible,
		   Estado estado,
		   Date fechaCompra,
		   Seguro seguro,
		   String userName)
		   //Autos categoria) 
	{
		 final Autos auto = newTransientInstance(Autos.class);
		   auto.setPatente(patente);
		   auto.setMarca(marca);
		   auto.setModelo(modelo);
		   auto.setAno(ano);
		   auto.setColor(color);
		   auto.setKilometraje(kms);
		   auto.setCapacidadBaul(baul);
		   auto.setTipoCombustible(combustible);
		   auto.setEstado(estado);
		   auto.setFechaCompra(fechaCompra);
		   auto.setSeguro(seguro);
		   auto.setOwnedBy(userName);
       
		        
       persist(auto);
       return auto;
    }
	
	@MemberOrder(sequence = "2")
	 public List<Autos> ListarAutos() {
		 final List<Autos> autos= allInstances(Autos.class);
		 return autos;
		}
	//}}
	
	//{{ Injected
	protected String currentUserName(){
		return getContainer().getUser().getName();
	}

}