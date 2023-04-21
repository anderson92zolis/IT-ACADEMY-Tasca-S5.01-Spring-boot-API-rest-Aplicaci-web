package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.repository;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.domain.FlowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IFlowerRepository extends JpaRepository<FlowerEntity,Integer>{
}
