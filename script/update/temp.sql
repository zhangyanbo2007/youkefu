
ALTER TABLE uk_callcenter_event ADD DISCALLER VARCHAR(50);
ALTER TABLE uk_callcenter_event ADD DISCALLED VARCHAR(50);

ALTER TABLE uk_chat_message ADD cooperation tinyint;
update uk_chat_message set cooperation = 0


UPDATE uk_callcenter_event SET DISCALLED = CALLED,DISCALLER = CALLER WHERE calltype <> 'orgcallout';
UPDATE uk_callcenter_event SET DISCALLED = CALLER,DISCALLER = CALLED WHERE calltype = 'orgcallout';
