package cn.panda.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zyp
 * @since 2022-07-05
 */
@Getter
@Setter
public class Porn91 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String viewTimes;

    private Date videoDownloadTime;

    private String imgUrl;

    private String title;

    private String pageUrl;

    private String videoPlayUrl;

    private String videoInfo;

    private Integer isImgDownload;

    private Integer isVideoDownload;

    private Integer clickTimes;


}

