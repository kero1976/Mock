package hoge.mock2.common.valueObject;

import java.io.File;

import hoge.mock2.common.exception.FileException;
import hoge.mock2.common.exception.FileException.ERR_KIND;


public class ExeFile {

	private String exeFilePath;

	public ExeFile(String filePath) throws FileException {
		if(filePath == null) {
			throw new FileException(ERR_KIND.VALUE_IS_NULL, filePath);
		}
		File file = new File(filePath);
//		try {
		    if (!file.exists()){
		    	throw new FileException(ERR_KIND. FILE_NOT_FOUND, filePath);
		    }else if(file.isDirectory()) {
		    	throw new FileException(ERR_KIND. VALUE_IS_DIR, filePath);
		    }
		    this.exeFilePath = filePath;
//		}catch(SecurityException e) {
//			throw new FileException(ERR_KIND. FILE_NOT_FOUND, filePath, e);
//		}

	}

	public File getExeFile() {
		return new File(exeFilePath);
	}
}
