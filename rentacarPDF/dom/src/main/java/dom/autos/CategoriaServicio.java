package dom.autos;

import java.util.List;

import org.apache.isis.applib.AbstractContainedObject;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.filter.Filter;

public class CategoriaServicio extends AbstractContainedObject
{
	  @MemberOrder(sequence = "1")
		public Categoria CargarCategoria(@Named("Categoria") String categoria,@Named("cantidad de puertas") int cantPuertas)
		   { 
		     return laCategoria(categoria,cantPuertas);
		   }
	  @Hidden 
	  public Categoria laCategoria(String categoria,int cantPuertas)  
		{
			 final Categoria auxCate = newTransientInstance(Categoria.class);
			   auxCate.setCategorias(categoria);
			   auxCate.setCantPuertas(cantPuertas);
			   persist(auxCate);
			   return auxCate;
		}
	  
	  @Hidden
	    public List<Categoria> completaComboCategoria(final String categoria)
	    {
	        return allMatches(Categoria.class, new Filter<Categoria>() 
	        {
	            @Override
	            public boolean accept(final Categoria t) 
	            {
	                return t.getCategorias().contains(categoria);

	            }

	        });
	    }	      
	
	
}
