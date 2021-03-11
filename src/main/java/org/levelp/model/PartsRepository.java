package org.levelp.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RepositoryRestResource(
        collectionResourceRel = "parts",
        itemResourceRel = "part"
)
public interface PartsRepository extends JpaRepository<Part, Integer> {
    public Part findByPartNumber(String partNumber);

    @Query("from Part part where part.storage.title = :title")
    public List<Part> findByStorageTitle(@Param("title") String title);

    @Query("from Part part where part.storage.title = :title")
    public Page<Part> findByStorageTitlePaging(
            @Param("title") String title,
            Pageable page
    );

    @Transactional
    public default Part saveNewPart(String partNumber, String title) {
        return save(new Part(partNumber, title));
    }
}
