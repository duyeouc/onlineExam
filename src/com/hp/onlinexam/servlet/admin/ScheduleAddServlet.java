package com.hp.onlinexam.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.onlinexam.po.Course;
import com.hp.onlinexam.po.StuClass;
import com.hp.onlinexam.po.Teacher;
import com.hp.onlinexam.po.TeacherCourse;
import com.hp.onlinexam.service.admin.CourseService;
import com.hp.onlinexam.service.admin.ICourseService;
import com.hp.onlinexam.service.admin.IScheduleService;
import com.hp.onlinexam.service.admin.IStuClassService;
import com.hp.onlinexam.service.admin.ITeacherService;
import com.hp.onlinexam.service.admin.ScheduleService;
import com.hp.onlinexam.service.admin.StuClassService;
import com.hp.onlinexam.service.admin.TeacherService;
@WebServlet("/scheduleAddServlet")
public class ScheduleAddServlet extends HttpServlet{
	IScheduleService ss = new ScheduleService();
	ITeacherService ts = new TeacherService();
	ICourseService cs  = new CourseService();
	IStuClassService scs = new StuClassService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Teacher> teaList= ts.findTeachers(null);
		List<StuClass> classList = scs.findAll();
		List<Course> couList = cs.findAllCourses(null);
		req.setAttribute("courseList", couList);
		req.setAttribute("teaList", teaList);
		req.setAttribute("stuclList", classList);
		
		req.getRequestDispatcher("/manager/scheduleadd.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String courseId = req.getParameter("course");
		String teacherId = req.getParameter("teacher");
		String stuclassId = req.getParameter("stuclass"); 
		TeacherCourse tc = new TeacherCourse();
		tc.setTeaId(Integer.parseInt(teacherId));
		tc.setCourseId(Integer.parseInt(courseId));
		tc.setClassId(Integer.parseInt(stuclassId));
		
		ss.addTeacherCourse(tc);
		resp.sendRedirect(req.getContextPath()+"/scheduleQueryServlet");
		
		
	}
}
