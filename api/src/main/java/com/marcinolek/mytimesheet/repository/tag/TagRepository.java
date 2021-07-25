package com.marcinolek.mytimesheet.repository.tag;

import com.marcinolek.mytimesheet.entity.tag.TagEntity;
import com.marcinolek.mytimesheet.repository.base.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends AbstractRepository<TagEntity, Long> {
}
