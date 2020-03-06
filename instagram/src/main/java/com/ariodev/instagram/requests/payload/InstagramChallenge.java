/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ariodev.instagram.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Login Result
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Getter
@Setter
@ToString
public class InstagramChallenge {
    private String url;
    private String api_path;
    private Boolean hide_webview_header;
    private Boolean lock;
    private Boolean logout;
    private Boolean native_flow;

}