package co.usa.ciclo3.ciclo3.repository;

import co.usa.ciclo3.ciclo3.model.Categoria;
import co.usa.ciclo3.ciclo3.model.Papeleria;
import co.usa.ciclo3.ciclo3.model.custom.CountCategoria;
import co.usa.ciclo3.ciclo3.repository.crud.PapeleriaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class PapeleriaRepository {

    @Autowired
    private PapeleriaCrudRepository papeleriaCrudRepository;

    public List<Papeleria> getAll(){
        return (List<Papeleria>) papeleriaCrudRepository.findAll();
    }
    public Optional<Papeleria> getPapeleria(int id){
        return  papeleriaCrudRepository.findById(id);
    }

    public Papeleria save(Papeleria p){
        return papeleriaCrudRepository.save(p);
    }

    public void delete(Papeleria p){
        papeleriaCrudRepository.delete(p);
    }


    public List<Papeleria> getPapeleriasByDescription(String description){
        return papeleriaCrudRepository.findAllByDescription(description);
    }

    public List<Papeleria> getPapeleriaPeriod(Date dateOne, Date dateTwo){
        return papeleriaCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne,dateTwo);
    }

    public List<CountCategoria> getTopCategorias(){
        List<CountCategoria> res=new ArrayList<>();

        List<Object[]> report=papeleriaCrudRepository.countTotalPapeleriaByCategoria();
        for(int i=0;i<report.size();i++){
            /*
            Categoria cat=(Categoria) report.get(i)[0];
            Integer cantidad=(Integer) report.get(i)[1];
            CountCategoria cc=new CountCategoria(cantidad,cat);
            res.add(cc);
            */
            res.add(new CountCategoria((Integer) report.get(i)[1],(Categoria)report.get(i)[0] ));
        }
        return res;
    }

}
