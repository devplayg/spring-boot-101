package kr.co.unisem.vms.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommonRepository {
    int selectTotalRows();
}
