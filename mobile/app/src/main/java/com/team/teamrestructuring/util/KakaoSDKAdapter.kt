package com.team.teamrestructuring.util

import com.kakao.auth.*


class KakaoSDKAdapter(context:ApplicationClass) : KakaoAdapter(){
    override fun getApplicationConfig(): IApplicationConfig {
        return IApplicationConfig {
            ApplicationClass.instance?.getApplicationClassContext()
        }
    }

    override fun getSessionConfig(): ISessionConfig {
        return object : ISessionConfig{
            override fun getAuthTypes(): Array<AuthType> {
                return arrayOf(AuthType.KAKAO_TALK_ONLY)
            }

            override fun isUsingWebviewTimer(): Boolean {
                return false
            }

            override fun isSecureMode(): Boolean {
                return true
            }

            override fun getApprovalType(): ApprovalType? {
                return ApprovalType.INDIVIDUAL
            }

            override fun isSaveFormData(): Boolean {
                return true
            }

        }
    }
}