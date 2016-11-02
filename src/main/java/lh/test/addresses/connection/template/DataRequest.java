package lh.test.addresses.connection.template;

import lh.test.addresses.data.DataObject;

public interface DataRequest {
	public DataObject[] retrieve(Object param);
}
