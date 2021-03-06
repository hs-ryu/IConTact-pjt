package com.myapp.backend.service;

import com.myapp.backend.dao.MenuDao;
import com.myapp.backend.dao.NoticeDao;
import com.myapp.backend.dao.ScheduleDao;
import com.myapp.backend.domain.dto.notice.*;
import com.myapp.backend.domain.entity.Menu;
import com.myapp.backend.domain.entity.Notice;
import com.myapp.backend.domain.entity.Schedule;
import com.myapp.backend.repository.MenuRepository;
import com.myapp.backend.repository.NoticeRepository;
import com.myapp.backend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoticeServiceImpl implements NoticeService {

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    NoticeDao noticeDao;

    @Autowired
    ScheduleDao scheduleDao;

    @Autowired
    MenuDao menuDao;

    // 일반 공지글 등록
    @Override
    public int noticeInsert(NoticeDto noticeDto) {
        Notice notice = new Notice();

        notice.setTitle(noticeDto.getTitle());
        notice.setContent(noticeDto.getContent());
        notice.setUserId(noticeDto.getUserId());
        notice.setClassCode(noticeDto.getClassCode());

        notice.setNoticeImgUrl(noticeDto.getNoticeImgUrl());

        if(noticeRepository.save(notice) != null){
            return SUCCESS;
        }

        return FAIL;
    }

    // 일정 등록
    @Override
    public int scheduleInsert(NoticeDto noticeDto) {

        Schedule schedule = new Schedule();

        schedule.setTitle(noticeDto.getTitle());
        schedule.setContent(noticeDto.getContent());
        schedule.setUserId(noticeDto.getUserId());
        schedule.setClassCode(noticeDto.getClassCode());
        schedule.setScheduleImgUrl(noticeDto.getScheduleImgUrl());

        if(scheduleRepository.save(schedule) != null){
            return SUCCESS;
        }

        return FAIL;
    }

    @Override
    public int menuInsert(NoticeDto noticeDto) {
        Menu menu = new Menu();

        menu.setAmSnack(noticeDto.getAmSnack());
        menu.setAmSnackImgUrl(noticeDto.getAmSnackImgUrl());
        menu.setLunch(noticeDto.getLunch());
        menu.setLunchImgUrl(noticeDto.getLunchImgUrl());
        menu.setPmSnack(noticeDto.getPmSnack());
        menu.setPmSnackImgUrl(noticeDto.getPmSnackImgUrl());
        menu.setUserId(noticeDto.getUserId());
        menu.setClassCode(noticeDto.getClassCode());

        if(menuRepository.save(menu) != null){
            return SUCCESS;
        }

        return FAIL;
    }

    @Override
    public int noticeUpdate(NoticeDto noticeDto) {
        Notice notice = noticeRepository.findByNoticeId(noticeDto.getId());
        if(notice != null){
            notice.setTitle(noticeDto.getTitle());
            notice.setContent(noticeDto.getContent());
            notice.setNoticeImgUrl(noticeDto.getNoticeImgUrl());
            
            if(noticeRepository.save(notice)!= null){
                return SUCCESS;
            }
        }
        return FAIL;
    }

    @Override
    public int scheduleUpdate(NoticeDto noticeDto) {
        Schedule schedule = scheduleRepository.findByScheduleId(noticeDto.getId());
        if(schedule != null){
            schedule.setTitle(noticeDto.getTitle());
            schedule.setContent(noticeDto.getContent());

            schedule.setScheduleImgUrl(noticeDto.getScheduleImgUrl());


            if(scheduleRepository.save(schedule) != null){
                return SUCCESS;
            }
        }
        return FAIL;
    }

    @Override
    public int menuUpdate(NoticeDto noticeDto) {
        Menu menu = menuRepository.findByMenuId(noticeDto.getId());
        if(menu != null){
            menu.setAmSnack(noticeDto.getAmSnack());
            menu.setAmSnackImgUrl(noticeDto.getAmSnackImgUrl());
            menu.setLunch(noticeDto.getLunch());
            menu.setLunchImgUrl(noticeDto.getLunchImgUrl());
            menu.setPmSnack(noticeDto.getPmSnack());
            menu.setPmSnackImgUrl(noticeDto.getPmSnackImgUrl());

            if(menuRepository.save(menu) != null){
                return SUCCESS;
            }
        }
        return FAIL;
    }

    @Override
    public int noticeDelete(int id) {
        try{
            noticeRepository.deleteByNoticeId(id);
        } catch (Exception e){
            return FAIL;
        }
        return SUCCESS;

    }

    @Override
    public int scheduleDelete(int id) {
        try{
            scheduleRepository.deleteByScheduleId(id);
        } catch (Exception e){
            return FAIL;
        }
        return SUCCESS;
    }

    @Override
    public int menuDelete(int id) {
        try{
            menuRepository.deleteByMenuId(id);
        } catch (Exception e){
            return FAIL;
        }
        return SUCCESS;
    }

    @Override
    public List<NoticeResultDto> noticeList(NoticeParamDto noticeParamDto) {
        return noticeDao.noticeList(noticeParamDto);
    }

    @Override
    public List<ScheduleResultDto> scheduleList(NoticeParamDto noticeParamDto) {
        return scheduleDao.scheduleList(noticeParamDto);
    }

    @Override
    public List<MenuResultDto> menuList(NoticeParamDto noticeParamDto) {
        return menuDao.menuList(noticeParamDto);
    }

    @Override
    public NoticeResultDto noticeDetail(int id) {
        return noticeDao.noticeDetail(id);
    }

    @Override
    public ScheduleResultDto scheduleDetail(int id) {
        return scheduleDao.scheduleDetail(id);
    }

    @Override
    public MenuResultDto menuDetail(int id) {
        return menuDao.menuDetail(id);
    }

    @Override
    public int totalNoticeList(String userId) {
        return noticeDao.totalNoticeList(userId);
    }

    @Override
    public int totalScheduleList(String userId) {
        return scheduleDao.totalScheduleList(userId);
    }

    @Override
    public int totalMenuList(String userId) {
        return menuDao.totalMenuList(userId);
    }
}
