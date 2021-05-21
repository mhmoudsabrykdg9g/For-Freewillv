package ftnt.qa.autotest.ui.dao;

import java.util.List;

import ftnt.qa.autotest.ui.dao.model.SupplierInfo;

public interface SupplierOperationDao {

	public SupplierInfo seletSupplierInfo(String emali);

	public List<SupplierInfo> seletSupplierInfo(List<String> emalis);

	public void updateSupplierInfo(SupplierInfo info);

	public void updateSupplierInfo(List<SupplierInfo> infos);

	public void logicDeleteSupplierInfo(String attr);

}