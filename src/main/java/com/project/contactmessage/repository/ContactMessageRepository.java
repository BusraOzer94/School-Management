package com.project.contactmessage.repository;

import com.project.contactmessage.entity.ContactMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.DoubleStream;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    Page<ContactMessage> findByEmailEquals(String email, Pageable pageable);

    Page<ContactMessage> findBySubjectEquals(String subject, Pageable pageable);

    @Query("SELECT c FROM ContactMessage c WHERE FUNCTION('DATE', c.dateTime) BETWEEN ?1 AND ?2")// 1--> 1.parametre 2--> 2.parametre
    List<ContactMessage> findMessagesBetweenDates(LocalDate beginDate, LocalDate endDate);

    @Query("SELECT c FROM ContactMessage c WHERE " + // Bir veri tipinin bir kismini cekmek icin extract kullanilir. Ex. hour u cekmek icin kullandik
            "(EXTRACT(HOUR FROM c.dateTime) BETWEEN :startHour AND :endHour) AND " +
            "(EXTRACT(HOUR FROM c.dateTime) != :startHour OR EXTRACT(MINUTE FROM c.dateTime) >= :startMinute) AND " + // baslama dakikasina gore kontrol
            "(EXTRACT(HOUR FROM c.dateTime) != :endHour OR EXTRACT(MINUTE FROM c.dateTime) <= :endMinute)") // bitis dakikasina gore kontrol
    List<ContactMessage> findMessagesBetweenTimes(@Param("startHour") int startHour,
                                                  @Param("startMinute") int startMinute,
                                                  @Param("endHour") int endHour,
                                                  @Param("endMinute") int endMinute);

    	/*
		Yukardaki Query icin ornek senaryo :
		Db deki ContactMessage  saati 09:30
		Sorguda gelen parametreler --> 09:15 / 17:15
		Ilk AND --> TRUE
		ikinci AND --> False OR True
		ucuncu AND --> True OR False
	 */ // Query aciklama


}