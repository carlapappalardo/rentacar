package dom.autos;


import java.util.ArrayList;
import java.util.List;


import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.util.TitleBuffer;
import org.apache.isis.core.objectstore.jdo.applib.annotations.Auditable;
import org.apache.isis.applib.filter.Filter;


import com.google.common.base.Objects;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Queries({
@javax.jdo.annotations.Query(name="listado_autos", language="JDQL",value="SELECT FROM dom.autos.Marcas WHERE ownedBy == :ownedBy")})
@javax.jdo.annotations.Version(strategy=VersionStrategy.VERSION_NUMBER, column="VERSION")
@ObjectType("MARCAS")
@Auditable
@AutoComplete(repository=MarcasServicio.class, action="autoComplete")

public class Marcas {

	public String title() {
		final TitleBuffer buf = new TitleBuffer();		
		buf.append(getMarcas());		       
		return buf.toString();
		}
	
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
		  
	//{{ Marcas
	private String marca;
	@DescribedAs("La marca del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence="1")
	public String getMarcas(){
		return marca;
	}
	
	public void setMarcas(String marca){
		this.marca=marca;
	}	
	
	
	//{{ Autos
	@Persistent(mappedBy="marca")
	private List<Autos> autos = new ArrayList<Autos>();
	public List<Autos> getAutos() { 
		return autos; }
	public void setAutos(List<Autos> autos) { 
		this.autos= autos; }
	
	public Autos addToAutos(Autos a) {
//	        if(a == null || autos.contains(a)) return;
//	        a.setMarca(this);
//	        autos.add(a); 
	        getAutos().add(a);
	        return a;
	}
	public void removeFromAutos(Autos e) {
	        if(e == null || !autos.contains(e)) return;
	        e.setMarca(null);
	        autos.remove(e); }
		
	
	 public static Filter <Marcas> thoseOwnedBy(final String currentUser) {
	        return new Filter<Marcas>() {
	            @Override
	            public boolean accept(final Marcas marca) {
	                return Objects.equal(marca.getOwnedBy(), currentUser);
	            }
	        };
	    }
	
	
	 // {{ injected: DomainObjectContainer
    @SuppressWarnings("unused")
    private DomainObjectContainer container;

    public void setDomainObjectContainer(final DomainObjectContainer container) {
        this.container = container;
    }
    // }}
}
