package com.jtool.codegendemo.api.response;

import com.jtool.codegenannotation.CodeGenField;

import javax.validation.constraints.NotNull;

public class UploadAvatarApiResponse {

    @NotNull
    @CodeGenField("状态码, 0：完成")
    private String code;

    @CodeGenField("文件base64")
    private String fileContent;

    @NotNull
    @CodeGenField("文件md5")
    private String md5;

    @NotNull
    private Integer seq;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "UploadAvatarResponse{" +
                "code='" + code + '\'' +
                ", fileContent='" + fileContent + '\'' +
                '}';
    }
}
