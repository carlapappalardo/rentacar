package dom.autos;


import java.util.Date;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.VersionStrategy;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.filter.Filter;
import org.apache.isis.applib.util.TitleBuffer;
import org.apache.isis.core.objectstore.jdo.applib.annotations.Auditable;

import com.google.common.base.Objects;



@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
//@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Queries({
@javax.jdo.annotations.Query(name="listado_autos", language="JDQL",value="SELECT FROM dom.autos.Autos WHERE ownedBy == :ownedBy")})
@javax.jdo.annotations.Version(strategy=VersionStrategy.VERSION_NUMBER, column="VERSION")

@ObjectType("AUTO")
@Auditable
@AutoComplete(repository=MarcasServicio.class, action="autoComplete")


public class Autos 
{
	/*
	public static enum Marca 
	{
        CHEVROLET, CITROEN, FIAT, FORD, RENAULT, TOYOTA, VOLKSWAGEN ;
    }*/
	public static enum TipoCombustible
	{
		NAFTA, DIESEL;
	}
	public static enum Estado{
		ALQUILADO, LIBRE, AVERIADO;
	}
	public static enum Seguro{
		LA_SEGUNDA, MAPFRE, LA_PATRONAL, LA_CAJA, ZURICH;		
	}
	
	// {{ Identification on the UI
	public String title() {
	final TitleBuffer buf = new TitleBuffer();
	
	buf.append(getPatente());	       
	       
	return buf.toString();
	}

	// }}
	  
	// {{ OwnedBy (property)
	private String ownedBy;

	@Hidden
	// not shown in the UI
	public String getOwnedBy() 
	{
	    return ownedBy;
	}

	public void setOwnedBy(final String ownedBy) 
	{
	    this.ownedBy = ownedBy;
	}	    
	  
	// {{ Patente
	@PrimaryKey
	private String patente;
	@DescribedAs("El dominio del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence="1")
	public String getPatente()
	{
		return patente;
	}
	
	public void setPatente(String patente)
	{
		this.patente=patente;
	}	
	
	
    // {{ Marca
	@Persistent
	private Marcas marca;
	//private List<Marcas> marca=new ArrayList<Marcas>();
	@DescribedAs("La marca del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence="2")
	
	public Marcas getMarca(){
		return marca;
	}	
	public void setMarca(final Marcas marca){		
		this.marca=marca;
	}	
	/*
	public void modificarMarca(Marcas a) {
	        if(a==null || marca==a) return;
	        if(marca != null) {
	            marca.removeFromAutos(this);
	        }
	        a.addToAutos(this);
	    }
	*/
	
	// {{ Modelo
    private String modelo;
    @DescribedAs("El modelo del vehiculo.")
    @RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
    // words, spaces and selected punctuation
    @MemberOrder(sequence = "3")
    public String getModelo() {
        return modelo;
    }

    public void setModelo(final String modelo)
    {
        this.modelo = modelo;
    }
    private int ano;
    @DescribedAs("El año del vehiculo.")
    @RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")

    @MemberOrder(sequence = "4")
    public int getAno() {
        return ano;
    }

    public void setAno(final int ano) {
        this.ano = ano;
    }


    private String color;
    @DescribedAs("El color del vehiculo.")
    @RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
    
    @MemberOrder(sequence = "5")
    public String getColor() 
    {
        return color;
    }

    public void setColor(final String color) 
    {
        this.color = color;
    }
    private int kms;
    @DescribedAs("El kilometraje del vehiculo.")
    @RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")

    @MemberOrder(sequence = "6")
    public int getKilometraje() 
    {
        return kms;
    }

    public void setKilometraje(final int kms) 
    {
        this.kms = kms;
    }
    private int baul;
    @DescribedAs("La capacidad del baul del vehiculo.")
    @RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")

    @MemberOrder(sequence = "7")
    public int getCapacidadBaul() 
    {
        return baul;
    }

    public void setCapacidadBaul(final int baul) 
    {
        this.baul = baul;
    }
 	private TipoCombustible combustible;
 	@DescribedAs("El tipo de combustible del vehiculo.")
 	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
 	@MemberOrder(sequence="8")
 	public TipoCombustible getTipoCombustible()
 	{
 		return combustible;
 	}
 	
 	public void setTipoCombustible(TipoCombustible combustible)
 	{
 		this.combustible=combustible;
 	}	
 	

  	private Estado estado;
  	@DescribedAs("Señala el estado actual del vehiculo.")
  	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
  	@MemberOrder(sequence="9")
  	public Estado getEstado()
  	{
  		return estado;
  	}
  	
  	public void setEstado(Estado estado)
  	{
  		this.estado=estado;
  	}	
  	

    private Date fechaCompra;
    @DescribedAs("Señala la fecha de compra del vehiculo.")
    @MemberOrder(sequence="10")
    public Date getFechaCompra() 
    {
        return fechaCompra;
    }

    public void setFechaCompra(final Date fechaCompra)
    {
        this.fechaCompra= fechaCompra;
    }  
    
    public void clearFechaCompra()
    {
        setFechaCompra(null);
    }
 
 // {{ Seguro del vehiculo
   	private Seguro seguro;
   	@DescribedAs("Señala el seguro del vehiculo.")
   	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
   	@MemberOrder(sequence="11")
   	public Seguro getSeguro()
   	{
   		return seguro;
   	}
   	
   	public void setSeguro(Seguro seguro)
   	{
   		this.seguro=seguro;
   	}	
   	
   	/*
   	private Autos categoria;
    @MemberOrder(sequence="12")
    public Autos getCategoria(Categoria hola) 
    {
    	
    	//toDoItem.getDependencies().add(toDoItem);
        return categoria;
    }

    public void setCategoria(final Autos categoria)
    {
        this.categoria=categoria;
    }
    /*
    public Autos Add(Categoria toDoItem) 
    {
       toDoItem.getDependencies().add(toDoItem);
        return this;
    } */   
   	
   	
    public static Filter<Autos> thoseOwnedBy(final String currentUser) {
        return new Filter<Autos>() {
            @Override
            public boolean accept(final Autos auto) {
                return Objects.equal(auto.getOwnedBy(), currentUser);
            }

        };
    }
  
    
    // {{ injected: DomainObjectContainer
    @SuppressWarnings("unused")
    private DomainObjectContainer container;

    public void setDomainObjectContainer(final DomainObjectContainer container)
    {
        this.container = container;
    }
    // }}   

	
	
	
}
