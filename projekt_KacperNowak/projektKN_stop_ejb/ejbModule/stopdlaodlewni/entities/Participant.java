package stopdlaodlewni.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the participant database table.
 * 
 */
@Entity
@NamedQuery(name="Participant.findAll", query="SELECT p FROM Participant p")
public class Participant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int participantID;

	@Lob
	private String description;

	@Temporal(TemporalType.DATE)
	private Date participeDate;

	private String typeOfMember;

	private double valueLoss;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UsersLogin")
	private User user;

	public Participant() {
	}

	public int getParticipantID() {
		return this.participantID;
	}

	public void setParticipantID(int participantID) {
		this.participantID = participantID;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getParticipeDate() {
		return this.participeDate;
	}

	public void setParticipeDate(Date participeDate) {
		this.participeDate = participeDate;
	}

	public String getTypeOfMember() {
		return this.typeOfMember;
	}

	public void setTypeOfMember(String typeOfMember) {
		this.typeOfMember = typeOfMember;
	}

	public double getValueLoss() {
		return this.valueLoss;
	}

	public void setValueLoss(double valueLoss) {
		this.valueLoss = valueLoss;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}