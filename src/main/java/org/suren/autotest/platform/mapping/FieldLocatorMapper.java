package org.suren.autotest.platform.mapping;

import org.suren.autotest.platform.entity.FieldLocator;
import org.suren.autotest.platform.model.Options;

import java.util.List;

/**
 * @author surenpi
 */
public interface FieldLocatorMapper extends BaseMapper<FieldLocator>
{
    /**
     * 根据元素（字段）ID来查找所有的定位信息
     * @param fieldId 元素ID
     * @return 定位信息列表
     */
    List<FieldLocator> getAllByFieldId(String fieldId);
}
