package org.suren.autotest.platform.mapping;

import org.suren.autotest.platform.entity.FieldLocator;
import org.suren.autotest.platform.model.DataSourceDetail;

import java.util.List;

public interface DataSourceDetailMapper extends BaseMapper<DataSourceDetail>
{
    List<DataSourceDetail> findByDataSourceId(String dataSourceId);
}