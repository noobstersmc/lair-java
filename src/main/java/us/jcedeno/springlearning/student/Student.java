package us.jcedeno.springlearning.student;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/** Lombok */
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Data
/** Springboot */
@Entity
@Table
public class Student {
	@Id
	@SequenceGenerator(
		name = "student_sequence",
		sequenceName =  "student_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "student_sequence"
	)
    private Long id;
    private @NonNull String name;
    private @NonNull String email;
    private @NonNull LocalDate dob;

	@Transient
    private Integer age;
	
	public Student(Long id, @NonNull String name, @NonNull String email, @NonNull LocalDate dob) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	/**
	 * Method to calculate the age from DOB. This overrides lombok default
	 * @return Years since the DOB of the entity.
	 */
	public Integer getAge(){
		return Period.between(dob, LocalDate.now()).getYears();
	}

	
}
