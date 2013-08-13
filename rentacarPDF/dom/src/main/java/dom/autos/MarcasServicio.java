package dom.autos;

import java.util.List;

import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.filter.Filter;
import org.apache.isis.applib.AbstractContainedObject;


import com.google.common.base.Objects;

@Named("Marcas")
public class MarcasServicio extends AbstractContainedObject{

	
	//{{ Marcas
		@MemberOrder(sequence = "1")
		public Marcas CargarMarca(@Named("Marca") String marcas) { 
			 final String ownedBy = currentUserName();
		     return laMarca(marcas, ownedBy);
		   }
		  @Hidden 
		 public Marcas laMarca(
			   String marcas,
			   String userName)  
		{
			 final Marcas aux = newTransientInstance(Marcas.class);
			   aux.setMarcas(marcas);
			   aux.setOwnedBy(userName);
			   persist(aux);
			   return aux;
		}
		
		@MemberOrder(sequence = "2")
		public List<Marcas> ListarMarcas()	{
			final List<Marcas> aux= allInstances(Marcas.class);
			
			return aux;
	    }
		
		@Hidden    
		public List<Marcas> autoComplete(final String marcas){
			return allMatches(Marcas.class, new Filter<Marcas>(){
				 		@Override
				 		public boolean accept(final Marcas t) {		
		                return ownedByCurrentUser(t) && t.getMarcas().contains(marcas);
				 		}
					});
			
		}
		//}}
				
			  
		//{{ Injected	 
		protected boolean ownedByCurrentUser(final Marcas t){
		    return Objects.equal(t.getOwnedBy(), currentUserName());
		}		

		protected String currentUserName(){
			return getContainer().getUser().getName();
		}
}
