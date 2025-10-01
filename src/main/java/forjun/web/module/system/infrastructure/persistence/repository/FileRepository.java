package forjun.web.module.system.infrastructure.persistence.repository;

import forjun.web.module.system.infrastructure.persistence.entity.UploadFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<UploadFileEntity, Integer> {

    //파일 ID로 메타데이터 삭제.
    public void deleteByFileId(String fileName);
}
