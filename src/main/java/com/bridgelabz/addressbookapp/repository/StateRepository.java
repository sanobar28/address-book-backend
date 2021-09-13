package com.bridgelabz.addressbookapp.repository;


import com.bridgelabz.addressbookapp.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@Repository
public interface StateRepository  extends JpaRepository<State, Integer> {
}
