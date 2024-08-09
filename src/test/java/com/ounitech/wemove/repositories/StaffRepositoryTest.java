package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Role;
import com.ounitech.wemove.models.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StaffRepositoryTest {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Role managerRole = new Role();
        managerRole.setRoleName("Manager");

        Role trainerRole = new Role();
        trainerRole.setRoleName("Trainer");

        testEntityManager.persist(managerRole);
        testEntityManager.persist(trainerRole);

        Staff staffOne = new Staff();
        staffOne.setFirstname("kevin");
        staffOne.setLastname("jones");
        staffOne.setActive(true);
        staffOne.setAddress("3 london street");
        staffOne.setPhone("31 48 53 65");
        staffOne.setPicture("https://dev.wemove.com/kevin.jones.jpg");
        staffOne.setEmail("kevin.jones@gmail.com");
        staffOne.setRole(managerRole);

        Staff staffTwo = new Staff();
        staffTwo.setFirstname("leila");
        staffTwo.setLastname("robinson");
        staffTwo.setActive(true);
        staffTwo.setAddress("5 mandela avenue");
        staffTwo.setPhone("51 24 85 62");
        staffTwo.setPicture("https://dev.wemove.com/leila.robinson.jpg");
        staffTwo.setEmail("leila.robinson@gmail.com");
        staffTwo.setRole(trainerRole);

        Staff staffThree = new Staff();
        staffThree.setFirstname("adam");
        staffThree.setLastname("wood");
        staffThree.setActive(false);
        staffThree.setAddress("7 palm springs corner");
        staffThree.setPhone("71 12 53 68");
        staffThree.setPicture("https://dev.wemove.com/adam.wood.jpg");
        staffThree.setEmail("adam.wood@gmail.com");
        staffThree.setRole(trainerRole);

        staffRepository.saveAll(List.of(staffOne, staffTwo, staffThree));
    }

    @Test
    void findByFirstname() {
        // Given // When
        List<Staff> results = staffRepository.findByFirstname("kevin");

        // Then
        assertThat(results)
                .hasSize(1)
                .extracting(Staff::getLastname)
                .containsExactly("jones");
    }

    @Test
    void findByActive() {
        // Given // When
        List<Staff> results = staffRepository.findByActive(true);

        // Then
        assertThat(results)
                .hasSize(2)
                .extracting(Staff::getLastname)
                .containsExactlyInAnyOrder("jones", "robinson");
    }

    @Test
    void findByEmail() {
        // Given // When
        Optional<Staff> result = staffRepository.findByEmail("leila.robinson@gmail.com");

        // Then
        assertThat(result)
                .isNotEmpty()
                .get()
                .extracting(Staff::getLastname)
                .isEqualTo("robinson");
    }

    @Test
    void findByEmail_unknown() {
        // Given // When
        Optional<Staff> result = staffRepository.findByEmail("ghost@gmail.com");

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void findByExample() {
        // Given
        Staff staff = new Staff();
        staff.setLastname("jones");
        staff.setActive(true);
        Example<Staff> example = Example.of(staff);

        // When
        List<Staff> result = staffRepository.findAll(example);

        // Then
        assertThat(result)
                .hasSize(1)
                .extracting(Staff::getFirstname)
                .containsExactly("kevin");
    }

}