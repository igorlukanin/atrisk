/**
 * function for translating using Bing's Translator API
 * @param String text - text to be translated
 * @param String sl - source language code (f.e. 'ru')
 * @param String tl - target language code (f.e. 'ru')
 * @param Function success - function to be called when translate is complete
 *        sucessfully (will be given to a jQuery's ajax success parameter)
 * @param Function complete - function to be called anyway (will be given to a
 *        jQuery's ajax complete parameter)
 * @link  http://habrahabr.ru/blogs/javascript/133940/
 */
function translate_bing(text, sl, tl, success, complete){
    if (typeof(complete) != 'function') {
        complete = function() {};
    }

    jQuery.ajax({
        url: 'http://api.bing.net/json.aspx?JsonCallback=?',
        dataType: 'jsonp',
        data: {
            'AppId': '8D73E60108A835729D94BEC43450E80233859F44',
            'Query': text.substr(0, 5000),
            'Sources': 'Translation',
            'Version': '2.2',
            'Translation.SourceLanguage': sl,
            'Translation.TargetLanguage': tl,
            'JsonType': 'callback'
        },
        success: function(response) {
            success(response.SearchResponse.Translation.Results[0].TranslatedTerm || '');
        },
        complete: complete
    });
}