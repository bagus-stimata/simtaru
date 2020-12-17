package com.desgreen.gov.database.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_rapat")
public class TbRapat {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    @ManyToOne
    @JoinColumn(name = "permohonan_bean", referencedColumnName = "id")
	private TbPermohonan permohonanBean;

	private LocalDate jadwalRapat = LocalDate.now();
	private LocalDate realisasiRapat = LocalDate.now();
	private String pesertaRapat = "";
	private String yangHadirRapat = "";
	private String hasilRapat = "";


	@OneToMany(mappedBy = "rapatBean")
	private List<TbRapatDocs> listRapatDocs;



}