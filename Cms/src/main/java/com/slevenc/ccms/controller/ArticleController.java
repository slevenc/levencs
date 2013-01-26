package com.slevenc.ccms.controller;

import com.slevenc.ccms.entity.article.Article;
import com.slevenc.ccms.entity.article.StaticFile;
import com.slevenc.ccms.service.article.ArticleService;
import com.slevenc.ccms.util.RequestUtil;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-26
 * Time: 下午2:32
 */
@Controller
@RequestMapping("article")
public class ArticleController {
    private ArticleService as = null;
    private RequestUtil ru = null;

    @RequestMapping("/")
    public String articleIndex(HttpSession session) {
        String view = "redirect:post/";
        return view;
    }


    @RequestMapping(value = "post/", method = RequestMethod.GET)
    public String postIndex(ModelMap mm, HttpServletRequest request) {
        String view = "article/postArticle.ftl";

        return view;
    }

    @RequestMapping(value = "post/", method = RequestMethod.POST)
    public String postArticle(ModelMap mm,
                              @RequestParam(value = "title", required = true) @Length(max = 255) String title,
                              @RequestParam(value = "content", required = true) String content) {
        String view = "article/postSuccess.ftl";
        Article a = new Article();
        a.setTitle(title);
        a.setContent(content);
        as.saveArticle(a);
        return view;

    }

    private Pattern numPattern = Pattern.compile("\\d+");

    @RequestMapping(value = "view/{ids}")
    public void viewArticle(HttpServletResponse response,
                            @PathVariable String ids) throws IOException {
        boolean success = false;
        Matcher matcher = numPattern.matcher(ids);
        if (matcher.find()) {
            long articleId = Long.parseLong(matcher.group());
            StaticFile staticFile = as.getArticleView(articleId);
            if (staticFile != null) {
                File parentFile = new File(as.getArticleDirValue());
                File articleFile = new File(parentFile, staticFile.getUri());
                if (articleFile.exists()) {
                    response.setContentType("text/html");
                    response.setCharacterEncoding("utf-8");
                    byte[] buffer = new byte[5 * 1024];
                    int len = -1;
                    InputStream in = null;
                    try {
                        in = new FileInputStream(articleFile);
                        while ((len = in.read(buffer)) > 1) {
                            response.getOutputStream().write(buffer, 0, len);
                        }
                        success = true;
                    } catch (Exception ex) {
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e) {
                            }
                        }
                    }
                }
            }
        }
        if (!success) {
            response.sendError(404);
        }

    }

    @Resource
    public void setAs(ArticleService as) {
        this.as = as;
    }

    @Resource
    public void setRu(RequestUtil ru) {
        this.ru = ru;
    }
}
