package cn.et.food.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.DeptMapper;
import cn.et.food.dao.EmpMapper;
import cn.et.food.entity.Dept;
import cn.et.food.entity.DeptExample;
import cn.et.food.entity.Emp;
import cn.et.food.entity.EmpExample;
import cn.et.food.entity.TreeNode;
import cn.et.food.service.DeptService;
@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	DeptMapper sm;
	@Autowired
	EmpMapper sm1;
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.StudentService#queryStudent(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.DeptService#queryTreeNode(java.lang.Integer)
	 */
	public List<TreeNode> queryTreeNode(Integer pid){
	
		DeptExample se=new DeptExample();
		se.createCriteria().andPidEqualTo(pid);
		List<Dept> dept=sm.selectByExample(se);
		List<TreeNode> deptList=new ArrayList<TreeNode>();
		for(Dept d:dept){
			TreeNode tn=new TreeNode();
			tn.setId(d.getId());
			tn.setText(d.getDname());
			//�жϵ�ǰ�ڵ����Ƿ񻹴����ӽڵ�
			if(queryTreeNode(d.getId()).size()==0){
				tn.setState("open");
			}
			deptList.add(tn);
		}
		return deptList;
	}
	public List<Emp> queryEmp(Integer id){
		EmpExample ee=new EmpExample();
		if(id!=null)
			ee.createCriteria().andDeptidEqualTo(id);
		return sm1.selectByExample(ee);
	}
	
}
