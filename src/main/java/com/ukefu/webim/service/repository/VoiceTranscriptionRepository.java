package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.VoiceTranscription;

public abstract interface VoiceTranscriptionRepository
  extends JpaRepository<VoiceTranscription, String>
{
  public abstract VoiceTranscription findByIdAndOrgi(String paramString, String orgi);
  
  public abstract List<VoiceTranscription> findByCallidAndOrgi(String callid, String orgi);
  
}
