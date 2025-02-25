package edu.miu.cs.badgeandmembershipcontrol.repository;

import java.util.List;
import java.util.Optional;

import edu.miu.cs.badgeandmembershipcontrol.domain.Location;
import edu.miu.cs.badgeandmembershipcontrol.domain.LocationType;
import edu.miu.cs.badgeandmembershipcontrol.domain.Membership;
import edu.miu.cs.badgeandmembershipcontrol.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Long> {
	@Query("select p from Plan p join p.locations l on l.id=?1")
	Optional<List<Plan>> findPlansByLocation_Id(Long locationId);

	@Query("select p.locations from Plan p where p.id =?1")
	Optional<List<Location>> findLocationsByPlan_Id(Long planId);

	Optional<Plan> findPlansById(Long planId);

	@Query("select m.plan from Membership m join Member a where m.member = ?1")
	Optional<List<Plan>> findPlanByMember_Id(Long memberId);

	@Query("select m.plan from Membership m where m.id =?1")
	Optional<Plan> findPlanByMemberShip(Long membershipId);

	Optional<Plan> findPlanByName(String name);

}
