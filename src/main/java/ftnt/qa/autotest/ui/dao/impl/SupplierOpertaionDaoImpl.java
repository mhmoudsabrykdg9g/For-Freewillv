package ftnt.qa.autotest.ui.dao.impl;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import ftnt.qa.autotest.ui.dao.SupplierOperationDao;
import ftnt.qa.autotest.ui.dao.model.SupplierInfo;
import ftnt.qa.autotest.ui.framework.annotation.PageObject;
import ftnt.qa.autotest.ui.framework.init.InitMybatisSession;

/**
 * @author xin.wang 供应商数据库操作
 *
 */
@PageObject
public class SupplierOpertaionDaoImpl implements SupplierOperationDao {

	private static SqlSession session;

	public SupplierInfo seletSupplierInfo(String emali) {
		SupplierInfo si = null;
		session = InitMybatisSession.getSessionFactory().openSession();
		try {
			si = session.selectOne("com.qa.autotest.ui.dao.selectSupplierInfo", emali);
			session.commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return si;
	}

	public List<SupplierInfo> seletSupplierInfo(List<String> emalis) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateSupplierInfo(SupplierInfo info) {
		// TODO Auto-generated method stub

	}

	public void updateSupplierInfo(List<SupplierInfo> infos) {
		// TODO Auto-generated method stub

	}

	public void logicDeleteSupplierInfo(String email) {
		SupplierInfo si = null;
		session = InitMybatisSession.getSessionFactory().openSession(false);
		try {
			si = session.selectOne("com.qa.autotest.ui.dao.selectSupplierInfo", email);
			session.update("com.qa.autotest.ui.dao.deleteSupplier", si.getSupplier_id());
			session.update("com.qa.autotest.ui.dao.deleteSupplierInfo", si.getSupplier_id());
			session.update("com.qa.autotest.ui.dao.deleteSupplierInfoLog", si.getSupplier_id());
			session.update("com.qa.autotest.ui.dao.deleteSupplierLinkEffectiveness", si.getEmail());
			session.commit(true);
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}