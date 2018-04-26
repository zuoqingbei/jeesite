package com.qdch.portal.littleproject.entity;

import java.util.List;
import java.util.Map;

public class LittleProjectDto {
    private Object[] times;
    private Map<String,Object[]> timesMap ;
    private List<LittleProjectEntity> entities;
    private List<KeHuFenLei> indexs;
    
    public Object[] getTimes() {
        return times;
    }

    public void setTimes(Object[] times) {
        this.times = times;
    }

    public Map<String, Object[]> getTimesMap() {
        return timesMap;
    }

    public void setTimesMap(Map<String, Object[]> timesMap) {
        this.timesMap = timesMap;
    }

    public List<LittleProjectEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<LittleProjectEntity> entities) {
        this.entities = entities;
    }

	public List<KeHuFenLei> getIndexs() {
		return indexs;
	}

	public void setIndexs(List<KeHuFenLei> indexs) {
		this.indexs = indexs;
	}

}
