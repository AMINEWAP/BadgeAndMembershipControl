package edu.miu.cs.badgeandmembershipcontrol;

import edu.miu.cs.badgeandmembershipcontrol.domain.Location;
import edu.miu.cs.badgeandmembershipcontrol.domain.LocationType;
import edu.miu.cs.badgeandmembershipcontrol.domain.Plan;
import edu.miu.cs.badgeandmembershipcontrol.domain.TimeSlot;
import edu.miu.cs.badgeandmembershipcontrol.repository.LocationRepository;
import edu.miu.cs.badgeandmembershipcontrol.service.Impl.LocationServiceImpl;
import edu.miu.cs.badgeandmembershipcontrol.service.LocationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @Autowired
    @InjectMocks
    private LocationServiceImpl locationService;

    Location location1;
    Location location2;
    TimeSlot timeSlot1;
    TimeSlot timeSlot2;
    Plan plan1;
    Plan plan2;
    List<Location> locationList;
    List<Plan> planList;




    @BeforeEach
    public void setUp() {

        locationList = new ArrayList<>();
        plan1 = new Plan(1L,"Eating","eating in Argiro");
        plan2 = new Plan(1L,"studying","study in student lounge");
        planList = new ArrayList<>();
        planList.add(plan1);
        planList.add(plan2);
        timeSlot1= new TimeSlot(1L,LocalDateTime.of(2020,
                Month.JULY, 29, 8, 30, 40),LocalDateTime.of(2020,
                Month.JULY, 30, 12, 30, 4));
        timeSlot2 = new TimeSlot(1L,LocalDateTime.of(2020,
                Month.JULY, 29, 2, 30, 40),LocalDateTime.of(2020,
                Month.JULY, 30, 4, 30, 4));
        List<TimeSlot >timeSlotList = new ArrayList<>();
        timeSlotList.add(timeSlot2);
        timeSlotList.add(timeSlot1);

        location1 = new Location(1L,"Argiro","description",12, LocationType.valueOf("DINNING_HALL"),planList,timeSlotList);
        location2 = new Location(2L,"Argiro","description",20,LocationType.valueOf("DINNING_HALL"),planList,timeSlotList);

    }
    @AfterEach
    public void tearDonw() {
         locationList = null;
    }

    @Test
    void saveLocation() throws Exception{
        when(locationRepository.save(ArgumentMatchers.any())).thenReturn(location1);
        locationService.createLocation(location1);
        verify(locationRepository,times(1)).save(ArgumentMatchers.any());
    }


    @Test
    public void returnLocationById() {
        Mockito.when(locationRepository.findById(1L)).thenReturn(Optional.ofNullable(location1));
        assertThat(locationService.getLocation(location1.getId())).isEqualTo(location1);
        System.out.println(location1);
    }




}
