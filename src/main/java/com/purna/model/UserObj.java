package com.purna.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import jakarta.persistence.Index;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="AmazonProUser", indexes = {
    @Index(name = "idx_user_email", columnList = "email")
})
@SQLDelete(sql = "UPDATE amazon_pro_user SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted=false")
public class UserObj {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
    @Builder.Default
    private Boolean isDeleted = false;

}
