// eslint-disable-next-line camelcase
export function setCookie (c_name, value, expire) {
  var date = new Date()
  date.setSeconds(date.getSeconds() + expire)
  // eslint-disable-next-line camelcase
  document.cookie = c_name + '=' + escape(value) + '; expires=' + date.toGMTString()
  console.log(document.cookie)
}

// eslint-disable-next-line camelcase
export function getCookie (c_name) {
  if (document.cookie.length > 0) {
    // eslint-disable-next-line camelcase
    let c_start = document.cookie.indexOf(c_name + '=')
    // eslint-disable-next-line camelcase,eqeqeq
    if (c_start != -1) {
      // eslint-disable-next-line camelcase
      c_start = c_start + c_name.length + 1
      // eslint-disable-next-line camelcase
      let c_end = document.cookie.indexOf(';', c_start)
      // eslint-disable-next-line camelcase,eqeqeq
      if (c_end == -1) c_end = document.cookie.length
      return unescape(document.cookie.substring(c_start, c_end))
    }
  }
  return ''
}

// eslint-disable-next-line camelcase
export function delCookie (c_name) {
  setCookie(c_name, '', -1)
}
