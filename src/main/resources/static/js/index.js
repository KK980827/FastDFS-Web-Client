const vue = new Vue({
    el : "#app",
    data(){
        return {
            consts:{
                tokenKey : "token",
                historyKey: "historyUploadRecords"
            },
            ui:{
                title:"最棒的大开开",
                findToken: false,
                showHistory: false
            },
            info: {
                token : "",
                remember: false,
            },
            history:[]
        };
    },
    methods:{
        uploadSuccess(res, file){
            this.$notify({
                title: '上传完成',
                message:
                    "<div>" +
                    "<li><span style='color: darkgray'>文件：</span>"+file.name+"</li>" +
                    "</div>" +
                    "<div>" +
                    "   <li><span style='color: darkgray'>URL：</span>"+res.data.url+"</li>" +
                    "</div>",
                type: 'success',
                showClose:true,
                dangerouslyUseHTMLString: true,
                duration: 0,
                position: 'bottom-right'
            });
            this.history.push({
                fileName: file.name,
                url: res.data.url,
                date: this.dateFormat("yyyy-MM-dd HH:mm:ss", new Date())
            });
            window.localStorage.setItem(this.consts.historyKey, JSON.stringify(this.history));
        },
        uploadError(err, file){
            const res = eval("("+err.message+")");
            this.$notify({
                title: '上传失败',
                message: "文件："+file.name+"。错误原因："+res.error,
                type: 'error',
                showClose:true,
                duration: 0,
                position: 'bottom-right'
            });
        },
        overLimit(){
            this.$message({
                message : "一次最多允许上传5个文件",
                type: "warning",
                center: true,
                position: 'bottom-right',
                showClose: false
            });
        },
        upload(){
            if(this.info.token===undefined || this.info.token==""){
                this.$message({
                    message : "当前输入的令牌无效",
                    type: "error",
                    center: true,
                    showClose: false
                });
                return;
            }
            this.$refs.uploader.submit();
        },
        saveToken(status){
            if(!status){
                return;
            }
            if(this.info.token===undefined || this.info.token==""){
                this.$message({
                    message : "当前输入的令牌无效",
                    type: "error",
                    center: true,
                    showClose: false
                });
                this.info.remember = false;
                return;
            }
            window.localStorage.setItem(this.consts.tokenKey,btoa(this.info.token));
            this.$message({
                message : "令牌已保存，下次将自动读取并使用",
                type: "success",
                center: true,
                showClose: false
            });
            this.ui.findToken = true;
        },
        clearToken(){
            window.localStorage.removeItem(this.consts.tokenKey)
            this.$message({
                message : "历史令牌已删除",
                center: true,
                type: "success",
                showClose: false
            });
            this.info.token = "";
            this.info.remember = this.ui.findToken = false;
        },
        clearHistory(){
            const _this = this;
            this.$confirm('确定要清空您在本地存储的历史上传记录嘛，这并不意味着您会在服务器删除文件，该操作仅仅只是删除本地的URL记录？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                window.localStorage.removeItem(_this.consts.historyKey);
                this.$message({
                    message : "历史记录已经删除完成",
                    center: true,
                    type: "success",
                    showClose: false
                });
            }).catch(() => {});
        },
        dateFormat(fmt, date) {
            let ret;
            let opt = {
                "y+": date.getFullYear().toString(),        // 年
                "MM+": (date.getMonth() + 1).toString(),     // 月
                "d+": date.getDate().toString(),            // 日
                "H+": date.getHours().toString(),           // 时
                "m+": date.getMinutes().toString(),         // 分
                "s+": date.getSeconds().toString()          // 秒
            };
            for (let k in opt) {
                ret = new RegExp("(" + k + ")").exec(fmt);
                if (ret) {
                    fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
                };
            };
            return fmt;
        }
    },
    watch:{
        'ui.showHistory':{
            deep:true,
            handler: function(newV, oldV) {
                this.ui.title = !newV ? "最棒的大开开" : (this.history===undefined || this.history==null) ? "历史记录0条" : "历史记录："+this.history.length+"条";
            }
        }
    },
    mounted(){
        const token = window.localStorage.getItem(this.consts.tokenKey);
        const history = window.localStorage.getItem(this.consts.historyKey);
        if(history!=null){
            this.history = JSON.parse(history);
            this.$message({
                message : "发现"+this.history.length+"条历史上传记录，已同步完成!",
                center: true,
                type: "success",
                showClose: false
            });
        }
        if(token===null){
            return;
        }
        this.info.token = atob(token);
        this.ui.findToken = true;
    }
});