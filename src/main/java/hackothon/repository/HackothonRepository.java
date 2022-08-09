package hackothon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackothon.model.HackothonParticpant;

@Repository
public interface HackothonRepository extends JpaRepository<HackothonParticpant, Long>{

}
