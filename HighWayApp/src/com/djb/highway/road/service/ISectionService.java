package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.road.entity.SectionEntity;

public interface ISectionService {
	public void addSection(SectionEntity Section);

	public void deleteSection(SectionEntity Section);

	public void deleteSectionBatch(List<SectionEntity> list);

	public void updateSection(SectionEntity Section);

	public SectionEntity getObject(SectionEntity Section);

	public List<SectionEntity> getSectionList();

	public List<SectionEntity> getSectionList(SectionEntity Section);

}
