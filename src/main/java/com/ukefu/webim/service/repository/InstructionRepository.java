package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Instruction;

public abstract interface InstructionRepository  extends JpaRepository<Instruction, String>
{
  public abstract Instruction findByIdAndOrgi(String id , String orgi);
  
  public abstract Page<Instruction> findByOrgi(String orgi , Pageable paramPageable);
  
  public abstract Instruction findByNameAndOrgi(String name, String orgi);
  
  public abstract List<Instruction> findByOrgi(String orgi);
  
  public abstract List<Instruction> findBySnsidAndOrgi(String snsid, String orgi);
  
  public abstract Page<Instruction> findBySnsidAndOrgi(String snsid, String orgi  , Pageable paramPageable);
  
  public abstract long countByNameAndSnsidAndOrgi(String name , String snsid, String orgi);
  
  public abstract long countByNameAndSnsidAndOrgiAndIdNot(String name , String snsid, String orgi, String id);
}
