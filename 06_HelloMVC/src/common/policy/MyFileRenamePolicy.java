package common.policy;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldfile) {
		
		File newFile=null;
		do {
			// 날짜 + (시간) + 임의 값으로 rename 설정 
			long currentTime=System.currentTimeMillis();
			SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
			int rndNum=(int)(Math.random()*1000);
			
			// 파일명 처리하기 
			String oldName=oldfile.getName();
			int point =oldName.lastIndexOf(".");
			String ext="";
			if(point>-1) {
				ext=oldName.substring(point);
			}
//			String [] t=oldName.split(".");
			
			//새파일 생성 
			String newName="Kakaotalk_"+sf.format(new Date(currentTime))+"_"+rndNum+ext;
			newFile=new File(oldfile.getParent(),newName);
			
			
			
		}while(!createNewFile(newFile)); 
			
		
		return newFile;
	}
	
	private boolean createNewFile(File newFile) {
		try {
			return newFile.createNewFile();
		}catch(IOException e) {
			return false;
		}
	}
}
