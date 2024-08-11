package com.ounitech.wemove.services;

import com.ounitech.wemove.models.Role;
import com.ounitech.wemove.models.Staff;
import com.ounitech.wemove.repositories.StaffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StaffServiceTest {

    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private StaffService staffService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        // Given
        Staff input = new Staff();

        Staff saved = new Staff();
        saved.setId(1);

        when(staffRepository.save(input)).thenReturn(saved);

        // When
        Staff result = staffService.save(input);

        // Then
        assertThat(result)
                .isNotNull()
                .extracting(Staff::getId)
                .isEqualTo(1);
    }

    @Test
    void update() {
        // Given
        Role managerRole = new Role();
        managerRole.setRoleName("Manager");

        Integer id = 1;
        Staff staff = new Staff();
        staff.setFirstname("firstname");
        staff.setLastname("lastname");
        staff.setActive(true);
        staff.setEmail("email");
        staff.setPhone("phone");
        staff.setId(id);
        staff.setPicture("https://dev.wemove.com/kevin.jones.jpg");
        staff.setRole(managerRole);

        Staff updatedStaff = new Staff();
        updatedStaff.setFirstname("updatedfirstname");
        updatedStaff.setLastname("updatedlastname");
        updatedStaff.setActive(true);
        updatedStaff.setEmail("updatedemail");
        updatedStaff.setPhone("updatedphone");
        updatedStaff.setId(id);
        updatedStaff.setPicture("updated_https://dev.wemove.com/kevin.jones.jpg");
        updatedStaff.setRole(managerRole);

        when(staffRepository.findById(1)).thenReturn(Optional.of(staff));
        when(staffRepository.save(Mockito.any(Staff.class))).thenReturn(updatedStaff);

        // When
        Staff result = staffService.updateStaff(id, updatedStaff);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(updatedStaff);
        assertThat(result.getRole().getRoleName()).isEqualTo(updatedStaff.getRole().getRoleName());

        verify(staffRepository, times(1)).findById(1);
        verify(staffRepository, times(1)).save(staff);
    }

    @Test
    void activate() {
        // Given
        Staff toActivate = new Staff();
        toActivate.setId(1);

        Staff activated = new Staff();
        activated.setId(1);
        activated.setActive(true);

        when(staffRepository.findById(1))
                .thenReturn(Optional.of(toActivate));
        when(staffRepository.save(Mockito.any(Staff.class)))
                .thenReturn(activated);

        // When
        Staff result = staffService.activateStaff(1);

        // Then
        assertThat(result)
                .isNotNull()
                .extracting(Staff::getActive)
                .isEqualTo(true);
    }

    @Test
    void deactivate() {
        // Given
        Staff toDeactivate = new Staff();
        toDeactivate.setId(1);

        Staff deactivated = new Staff();
        deactivated.setId(1);
        deactivated.setActive(false);

        when(staffRepository.findById(1))
                .thenReturn(Optional.of(toDeactivate));
        when(staffRepository.save(Mockito.any(Staff.class)))
                .thenReturn(deactivated);

        // When
        Staff result = staffService.deactivateStaff(1);

        // Then
        assertThat(result)
                .isNotNull()
                .extracting(Staff::getActive)
                .isEqualTo(false);
    }

    @Test
    void deleteById() {
        // Given // When
        staffService.deleteById(1);

        // Then
        verify(staffRepository, times(1))
                .deleteById(1);
    }

    @Test
    void findAll() {
        // Given
        Staff staffOne = new Staff();
        Staff staffTwo = new Staff();
        Staff staffThree = new Staff();

        when(staffRepository.findAll()).thenReturn(List.of(staffOne, staffTwo, staffThree));

        // When
        List<Staff> result = staffService.findAll();

        // Then
        assertThat(result)
                .hasSize(3);
    }

    @Test
    void findById() {
        // Given
        Staff staff = new Staff();
        staff.setId(1);

        when(staffRepository.findById(1)).thenReturn(Optional.of(staff));

        // When
        Optional<Staff> result = staffService.findById(1);

        // Then
        assertThat(result)
                .isNotEmpty()
                .get()
                .extracting(Staff::getId).isEqualTo(1);
    }

    @Test
    void findByFirstName() {
        // Given
        Staff staff = new Staff();
        staff.setId(1);
        staff.setFirstname("lucas");

        when(staffRepository.findByFirstname("lucas"))
                .thenReturn(List.of(staff));

        // When
        List<Staff> result = staffService.findByFirstName("lucas");

        // Then
        assertThat(result)
                .isNotEmpty()
                .extracting(Staff::getId)
                .containsExactly(1);
    }

    @Test
    void findByActive() {
        // Given
        Staff staff = new Staff();
        staff.setId(1);
        staff.setActive(true);

        when(staffRepository.findByActive(true))
                .thenReturn(List.of(staff));

        // When
        List<Staff> result = staffService.findByActiveStaff();

        // Then
        assertThat(result)
                .isNotEmpty()
                .extracting(Staff::getId)
                .containsExactly(1);
    }

    @Test
    void findByInactive() {
        // Given
        Staff staff = new Staff();
        staff.setId(1);
        staff.setActive(false);

        when(staffRepository.findByActive(false))
                .thenReturn(List.of(staff));

        // When
        List<Staff> result = staffService.findByInactiveStaff();

        // Then
        assertThat(result)
                .isNotEmpty()
                .extracting(Staff::getId)
                .containsExactly(1);
    }

    @Test
    void findByEmail() {
        // Given
        Staff staff = new Staff();
        staff.setId(1);
        staff.setEmail("lucas@gmail.com");

        when(staffRepository.findByEmail("lucas@gmail.com"))
                .thenReturn(Optional.of(staff));

        // When
        Staff result = staffService.findByEmail("lucas@gmail.com");

        // Then
        assertThat(result)
                .isNotNull()
                .extracting(Staff::getId).isEqualTo(1);
    }
}