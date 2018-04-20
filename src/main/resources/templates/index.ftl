<#include "header.ftl" >
    <div class="zg-wrap zu-main clearfix " role="main">
        <div class="zu-main-content">
            <div class="zu-main-content-inner">
                <div class="zg-section" id="zh-home-list-title">
                    <i class="zg-icon zg-icon-feedlist"></i>最新动态
                    <input type="hidden" id="is-topstory">
                    <span class="zg-right zm-noti-cleaner-setting" style="list-style:none">
                        <a href="https://nowcoder.com/settings/filter" class="zg-link-gray-normal">
                            <i class="zg-icon zg-icon-settings"></i>设置</a></span>
                </div>
                <div class="zu-main-feed-con navigable" data-feedtype="topstory" id="zh-question-list" data-widget="navigable" data-navigable-options="{&quot;items&quot;:&quot;&gt; .zh-general-list .feed-content&quot;,&quot;offsetTop&quot;:-82}">
                    <a href="javascript:;" class="zu-main-feed-fresh-button" id="zh-main-feed-fresh-button" style="display:none"></a>
                    <div id="js-home-feed-list" class="zh-general-list topstory clearfix" data-init="{&quot;params&quot;: {}, &quot;nodename&quot;: &quot;TopStory2FeedList&quot;}" data-delayed="true" data-za-module="TopStoryFeedList">

            <#list vos as vo>
    <div class="feed-item folding feed-item-hook feed-item-2
                        " feed-item-a="" data-type="a" id="feed-2" data-za-module="FeedItem" data-za-index="">
                            <meta itemprop="ZReactor" data-id="389034" data-meta="{&quot;source_type&quot;: &quot;promotion_answer&quot;, &quot;voteups&quot;: 4168, &quot;comments&quot;: 69, &quot;source&quot;: []}">
                            <div class="feed-item-inner">
                                <div class="avatar">
                                    <a title=${vo.user.name} data-tip="p$t$amuro1230" class="zm-item-link-avatar" target="_blank" href="/user/${vo.user.id}">
                                        <img src=${vo.user.head_url} class="zm-item-img-avatar"></a>
                                </div>
                                <div class="feed-main">
                                    <div class="feed-content" data-za-module="AnswerItem">
                                        <meta itemprop="answer-id" content="389034">
                                        <meta itemprop="answer-url-token" content="13174385">
                                        <h2 class="feed-title">
                                            <a class="question_link" target="_blank" href="/question/${vo.question.id}">${vo.question.title}</a></h2>
                                        <div class="feed-question-detail-item">
                                            <div class="question-description-plain zm-editable-content"></div>
                                        </div>
                                        <div class="expandable entry-body">
                                            <div class="zm-item-vote">
                                                <a class="zm-item-vote-count js-expand js-vote-count" href="javascript:;" data-bind-votecount="">4168</a></div>
                                            <div class="zm-item-answer-author-info">
                                                <a class="author-link" data-tip="p$b$amuro1230" target="_blank" href="/user/${vo.user.id}">${vo.user.name}</a>
                                                ，${vo.question.created_date} </div>
                                            <div class="zm-item-vote-info" data-votecount="4168" data-za-module="VoteInfo">
                                                <span class="voters text">
                                                    <a href="#" class="more text">
                                                        <span class="js-voteCount">4168</span>&nbsp;人赞同</a></span>
                                            </div>
                                            <div class="zm-item-rich-text expandable js-collapse-body" data-resourceid="123114" data-action="/answer/content" data-author-name="李淼" data-entry-url="/question/19857995/answer/13174385">
                                                <div class="zh-summary summary clearfix">${vo.question.content}</div>
                                            </div>
                                        </div>
                                        <div class="feed-meta">
                                            <div class="zm-item-meta answer-actions clearfix js-contentActions">
                                                <div class="zm-meta-panel">
                                                    <a data-follow="q:link" class="follow-link zg-follow meta-item" href="javascript:;" id="sfb-123114">
                                                        <i class="z-icon-follow"></i>关注问题</a>
                                                    <a href="#" name="addcomment" class="meta-item toggle-comment js-toggleCommentBox">
                                                        <i class="z-icon-comment"></i>${vo.question.comment_count}条评论</a>


                                                    <button class="meta-item item-collapse js-collapse">
                                                        <i class="z-icon-fold"></i>收起</button>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
            </#list>
                    </div>
                    <a href="javascript:;" id="zh-load-more" data-method="next" class="zg-btn-white zg-r3px zu-button-more" style="">更多</a></div>
            </div>
        </div>
    </div>
  <!-- 分割线 -->
 <!-- <div class="modal-dialog-bg" aria-hidden="true" style="opacity: 0.5; width: 1366px; height: 1196px;"></div> -->
        <div class="modal-wrapper" aria-hidden="true" id="question_form" style="display:none">
            <div class="modal-dialog absolute-position" tabindex="0" role="dialog" aria-labelledby=":i" style="width: 550px; right=40%">
                <div class="modal-dialog-title">
                    <span class="modal-dialog-title-text" id=":i" role="heading">提问</span>
                    <span class="modal-dialog-title-close" role="button" tabindex="0" aria-label="Close" onclick="hiddden_question_form()"></span>
                </div>
                <div class="modal-dialog-content">
                    <div class="zh-add-question-form">
                        <form id="question" class="js-add-question-form" style="display: block;">
                            <div class="zg-section-big clearfix">
                                <div id="zm-modal-dialog-info-wrapper"></div>
                                <div style="display: none;position: relative;" id="zm-modal-dialog-warnmsg-wrapper">
                                    <div class="zm-modal-dialog-warnmsg zm-modal-dialog-guide-warn-message zg-r5px"></div>
                                    <a name="close" title="关闭" href="javascript:;" class="zu-global-notify-close" style="display:none">x</a>
                                    <span class="zm-modal-dialog-guide-title-msg"></span>
                                </div>
                                <div class="zg-form-text-input add-question-title-form" style="position: relative;">
                                    <textarea name="title" rows="1" class="zg-editor-input zu-seamless-input-origin-element" title="在这里输入问题" id="zh-question-suggest-title-content" aria-label="写下你的问题,不能为空值" placeholder="写下你的问题,不能为空值" role="combobox" aria-autocomplete="list" autocomplete="off" style="height: 22px;"></textarea>
                                </div>
                            </div>
                            <div class="zg-section-big">
                                <div class="add-question-section-title">问题说明（可选）：</div>
                                <div   id="zh-question-suggest-detail-container" class="zm-editable-status-editing">
                                    <div class="zm-editable-editor-wrap no-toolbar" style="">
                                        <div class="zm-editable-editor-outer">
                                            <div class="zm-editable-editor-field-wrap">

                                                    <textarea name="content" rows="6" class="zg-editor-input zu-seamless-input-origin-element"  id="zh-question-suggest-content-content" aria-label="写下你的问题描述，不能为空值" placeholder="写下你的问题描述，不能为空值" role="combobox" aria-autocomplete="list" autocomplete="off" style="height: 22px;"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="zm-command">
                                <!-- <span id="zh-question-form-tag-err">至少添加一个话题</span> -->
                                <a href="javascript:;" name="cancel" class="zm-command-cancel" onclick="hiddden_question_form()">取消</a>
                                <!-- <a  name="addq" class="zg-r5px zu-question-form-add zg-btn-blue" onclick="postquestion()">发布</a> -->
                                <input type="button" onclick="postquestion()" value="发布" />
                                <a name="jumpq" class="zg-r5px zg-btn-blue zu-question-form-jump" style="display:none;">查看问题</a></div>
                        </form>
                    </div>
                </div>
                <div class="modal-dialog-buttons" style="display: none;"></div>
            </div>
        </div>
<#include "footer.ftl" >