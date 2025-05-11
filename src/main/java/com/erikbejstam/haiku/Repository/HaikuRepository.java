package com.erikbejstam.haiku.Repository;

import com.erikbejstam.haiku.Model.Haiku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HaikuRepository extends JpaRepository<Haiku, Long> {

}
